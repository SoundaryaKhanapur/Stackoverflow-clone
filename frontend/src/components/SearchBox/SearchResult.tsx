import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom'
import axios from 'axios';
import HomeCard from '../Home/HomeCard';
import Styles from '../Home/Home.css';
import AskQuestionButton from '../AskQuestionButton/AskQuestionButton';
import Pagination from '../Pagination/Pagination';
import Header from '../Header/Header';

const SearchResult = () => {

    const location = useLocation()
    const { message } = location.state


    const [searchdata, setSearchdata] = useState([])

    useEffect(() => {
        axios
            .get(`http://localhost:8081/api/search/tagOrTitle?tag=${message}`)
            .then(response => {

                setSearchdata(response.data);

                console.log(response.data);

            });
    }, [message])

    const [pageNumber, setPageNumber] = useState(0);

    const questionsPerPage = 1;
    const pagesVisited = pageNumber * questionsPerPage;


    const displayQuestions = searchdata
        .slice(pagesVisited, pagesVisited + questionsPerPage)

    const pageCount = Math.ceil(searchdata.length / questionsPerPage);

    const changePage = ({ selected }) => {
        setPageNumber(selected);
    };



    return (
        <div>
            <Header />

            <div className={Styles.home}>
                <div className={Styles.questionhead}>
                    <h3 className={Styles.questionsheadline}>Search Result : {message}</h3>
                    <div className={Styles.askquestionbutton}>
                        <AskQuestionButton />
                    </div>
                </div>
                <div className={Styles.numofque}>
                    <span >
                        {new Intl.NumberFormat('en-IN').format(searchdata.length)} questions
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


}

export default SearchResult;