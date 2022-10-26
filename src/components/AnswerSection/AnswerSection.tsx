import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Styles from './AnswerSection.css';
import AnswerSectionCard from './AnswerSectionCard';


const AnswerSection = ({ questionId }) => {

    const [answerDetails, setanswerDetails] = useState([]);


    useEffect(() => {


        axios
            .get(`http://localhost:8081/api/answer/${questionId}`)
            .then(res => {
                setanswerDetails(res.data);

                console.log(res.data + "answer" + { questionId } + "answers")


            })

    }, [questionId])

    return (
        <div className={Styles.answerscontainer}>
            <h5>{answerDetails.length} Answers</h5>
            {
                answerDetails.map(({
                    body,
                    createdAt,
                    userId,
                    votes,
                    id

                }) => {
                    return <AnswerSectionCard
                        // questionId={questionId}
                        answerBody={body}
                        createdAt={createdAt}
                        userId={userId}
                        votes={votes}
                        id={id}
                    />
                })

            }

        </div>
    );
};

export default AnswerSection;