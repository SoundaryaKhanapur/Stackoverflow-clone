import React, { useEffect, useState } from 'react';
import Styles from './QuestionDetails.css';
import { useLocation } from 'react-router-dom'
import axios from 'axios';
import { Link } from 'react-router-dom';
import Moment from 'react-moment';
import AddComment from '../AddComment/AddComment';
import AnswerSection from '../AnswerSection/AnswerSection';
import TagCard from '../Home/TagCard';
import VotingCounter from '../VotingCounter/VotingCounter';
import CommentsCard from '../AddComment/CommentsCard';
import Header from '../Header/Header';

const QuestionDetails = () => {

    const location = useLocation()
    const { message } = location.state

    const [questionDetail, setquestionDetail] = useState([]);

    const [tagname, setTagname] = useState([]);

    const [votevalue, setVotevalue] = useState('');

    const [comments, setComments] = useState([]);

    const [ansBody, setAnsBody] = useState("");


    const handleKeyDown = (event) => {
        event.preventDefault();
        setAnsBody(event.target.value)

    }
    const handleAnswerSubmit = (event) => {
        event.preventDefault();
        console.log(ansBody)
        axios
            .post(`http://localhost:8081/api/answer`,
                {
                    "body": `${ansBody}`,
                    "questionId": `${message}`,
                    "userId": localStorage.getItem('userId')
                })
            .then(() => {

                window.location.reload(true);
            });
    }

    useEffect(() => {
        axios.all([
            axios.get(`http://localhost:8081/api/question/${message}`)
                .then(res => {
                    setquestionDetail(res.data);

                    console.log(res.data + "question details")

                    setTagname(res.data.tagnames)

                    setVotevalue(res.data.votes)

                }),

            axios.get(`http://localhost:8081/api/comments/${message}`)
                .then(res => {
                    setComments(res.data);

                    console.log(res.data + "comments value")
                })

        ])


    }, [])

    return (
        <div>
            <Header />

            <div className={Styles.question}>
                <div className={Styles.questiondetail}>
                    <div className={Styles.questionvoting}>
                        <VotingCounter
                            votes={votevalue}
                            id={questionDetail.questionId}
                            type='question'
                        />
                    </div>

                    <div className={Styles.details} >
                        <div className={Styles.header} >
                            <Link to={{
                                pathname: '/questionDetails',
                                state: { message: questionDetail.questionId }
                            }}
                                className={Styles.questionheading}>
                                {questionDetail.title}
                            </Link>
                        </div>

                        <div className={Styles.askedview}>
                            <div>
                                Asked: {<Moment fromNow>{questionDetail.createdAt}</Moment>
                                }
                            </div>
                            <div className={Styles.view}>
                                Viewed: {questionDetail.views}
                            </div>

                        </div>
                        <p className={Styles.questiondetails}>{questionDetail.body}</p>
                        <div className={Styles.tagbox}>

                            {/* <Link to={{
                            pathname: '/tagged',
                            state: { message: questionDetail.tagnames }
                        }}
                            className={Styles.tag}>
                            {questionDetail.tagnames}
                        </Link> */}

                            {

                                [tagname].map((tags, index) => {
                                    console.log(tags, index)
                                    return <TagCard
                                        key={index}
                                        tagname={tags}
                                    />
                                })
                            }

                        </div>
                        <div className={Styles.user}>
                            User:{questionDetail.username}

                        </div>
                        {/* <div className={Styles.answer}>
                        {questionDetail.answersCount} Answer
                    </div> */}
                        <AddComment
                            userId={questionDetail.userId}
                            id={questionDetail.questionId}
                        />

                        <div>
                            {

                                comments.map(({
                                    key: id,
                                    body,
                                    userId,
                                    createdAt
                                }) => {
                                    return <CommentsCard
                                        key={id}
                                        comments={body}
                                        userId={userId}
                                        createdAt={createdAt}
                                    />
                                })
                            }

                        </div>

                    </div>

                </div>

                <div>
                    <AnswerSection
                        questionId={questionDetail.questionId}
                    />
                </div>

                <div className={Styles.answerformcontainer}>
                    <div className={Styles.answerform}>
                        {/* <input
                            className={Styles.answerbox}
                            type="text"
                            placeholder="Your Answer"
                            onChange={handleKeyDown}
                        /> */}

                        <textarea
                            onChange={handleKeyDown}
                            className={Styles.answerbox}
                            placeholder="Your Answer"
                        ></textarea>

                        <button onClick={handleAnswerSubmit} className={Styles.postbtn}>
                            Post Your Answer
                        </button>
                    </div>
                </div>

            </div>
        </div>
    )
}


export default QuestionDetails;