import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Styles from './Home.css';
import HomeCard from './HomeCard';
import AskQuestionButton from '../AskQuestionButton/AskQuestionButton';
import Pagination from '../Pagination/Pagination';
import Header from '../Header/Header';

const Questions = () => {

    const [question, setQuestion] = useState([]);
    const [pageNumber, setPageNumber] = useState(0);

    const questionsPerPage = 3;
    const pagesVisited = pageNumber * questionsPerPage;



    useEffect(() => {
        axios
            .get(`http://localhost:8081/api/question`)
            .then(res => {
                setQuestion(res.data);

                console.log(res.data);

            })
    }, [])

    const displayQuestions = question
        .slice(pagesVisited, pagesVisited + questionsPerPage)

    const pageCount = Math.ceil(question.length / questionsPerPage);

    const changePage = ({ selected }) => {
        setPageNumber(selected);
    };


    return (
        <div>
            <Header />

            <div className={Styles.home}>
                <div className={Styles.questionhead}>
                    <h3 className={Styles.questionsheadline}>Questions</h3>
                    <div className={Styles.askquestionbutton}>
                        <AskQuestionButton />
                    </div>
                </div>
                <div className={Styles.numofque}>
                    <span >
                        {new Intl.NumberFormat('en-IN').format(question.length)} questions
                    </span>

                </div>

                <div className={Styles.homecardcontainer} >
                    {
                        displayQuestions.map(({
                            key: id,
                            title,
                            body,
                            questionId,
                            tagnames,
                            views,
                            answersCount,
                            createdAt,
                            votes
                        }) => {
                            return <HomeCard
                                key={questionId}
                                question={title}
                                questionDetails={body}
                                questionId={questionId}
                                tagname={tagnames}
                                views={views}
                                answersCount={answersCount}
                                createdAt={createdAt}
                                votes={votes}
                            />
                        })
                    }

                </div>

                <div>
                    <Pagination
                        pageCount={pageCount}
                        changePage={changePage}
                    />
                </div>
            </div>
        </div>
    )


};

export default Questions;
