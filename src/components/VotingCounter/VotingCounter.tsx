import React, { useState, useEffect } from "react";
import Styles from './VotingCounter.css';
import axios from 'axios';

const VotingCounter = ({ votes, id, type }) => {

    // Create handleIncrement event handler
    const handleIncrement = () => {
        axios
            .post(`http://localhost:8081/api/${type}/vote/${id}/up`)
            .then(() => {

                window.location.reload(true);
            });

    };

    //Create handleDecrement event handler
    const handleDecrement = () => {
        axios
            .post(`http://localhost:8081/api/${type}/vote/${id}/down`)
            .then(() => {

                window.location.reload(true);
            })
    };

    if (parseInt(votes) < -2) {

        axios
            .post(`http://localhost:8081/api/answer/${id}/false`)
        // .then(() => {


        // })

        return <div className={Styles.invalid}>X</div>
    }


    return (

        <div className={Styles.votingcontainer}>
            <div>
                <button className={Styles.button} onClick={handleIncrement}>+</button>
                <div>
                    {votes}
                </div>
                <button className={Styles.button} onClick={handleDecrement}>-</button>
            </div>
        </div>
    )
}


export default VotingCounter;