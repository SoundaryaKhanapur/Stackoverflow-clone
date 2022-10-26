import React, { useEffect, useState } from 'react';
import Styles from './AnswerSection.css';
import Moment from 'react-moment';
import VotingCounter from '../VotingCounter/VotingCounter';
import AddComment from '../AddComment/AddComment';
import CommentsCard from '../AddComment/CommentsCard';
import axios from 'axios';

const AnswerSectionCard = ({
    answerBody,
    createdAt,
    userId,
    votes,
    id,

}) => {

    const [answercomment, setAnswercomment] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8081/api/comments/${id}`)
            .then(res => {
                setAnswercomment(res.data);

                console.log(res.data + "comments value from answer")
            })

    }, [])

    return (
        <div>
            <div className={Styles.answers}>
                <div className={Styles.answervoting}>
                    <VotingCounter
                        votes={votes}
                        id={id}
                        type='answer'
                    />
                </div>
                <div className={Styles.answersbody}>
                    <div className={Styles.answerheader}>
                        {answerBody}
                    </div>
                    <div className={Styles.asked}>
                        Asked: {<Moment fromNow>{createdAt}</Moment>}
                    </div>
                    <div className={Styles.answeruser}>
                        User: {userId}
                    </div>
                    {/* <div className={Styles.answerheader}>
                        Votes: {votes}
                    </div> */}

                    <div>
                        <AddComment
                            userId={userId}
                            id={id}
                        />
                    </div>
                    <div>
                        {

                            answercomment.map(({
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

        </div>
    );
};

export default AnswerSectionCard;