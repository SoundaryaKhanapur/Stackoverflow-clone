import React, { useEffect, useState } from 'react';
import Styles from './TagQuestions.css';
import { useLocation } from 'react-router-dom'
import axios from 'axios';
import HomeCard from '../Home/HomeCard';
import AskQuestionButton from '../AskQuestionButton/AskQuestionButton';
import Header from '../Header/Header';
import Pagination from '../Pagination/Pagination';

const TagQuestions = () => {

    const location = useLocation()
    const { message } = location.state

    const [tagquestions, setTagquestions] = useState([]);

    const [pageNumber, setPageNumber] = useState(0);

    const questionsPerPage = 10;
    const pagesVisited = pageNumber * questionsPerPage;


    useEffect(() => {
        axios
            .get(`http://localhost:8081/api/question/tag/${message}`)
            .then(res => {
                setTagquestions(res.data);

                console.log(res.data)

            })

    }, [])

    const displayQuestions = tagquestions
        .slice(pagesVisited, pagesVisited + questionsPerPage)

    const pageCount = Math.ceil(tagquestions.length / questionsPerPage);

    const changePage = ({ selected }) => {
        setPageNumber(selected);
    };


    return (
        <div>
            <Header />

            <div className={Styles.tagquestion}>
                <div className={Styles.tagheader}>
                    <h4 className={Styles.tagheading}>Questions tagged [{message}]</h4>
                    <AskQuestionButton />
                </div>
                <div className={Styles.cardcontainer} >
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
    );
}

export default TagQuestions;