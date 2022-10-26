import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Styles from './AddComment.css';


const AddComment = ({
    userId,
    id }) => {

    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {


            console.log(event.target.value)

            // const json = {
            //     "body": { comment },
            //     "userId": "12803",
            //     "questionId": "xk7VvHwBkAsE4HoO2KkZ"
            // };

            axios
                .post(`http://localhost:8081/api/comments`,
                    {
                        "body": `${event.target.value}`,
                        "userId": localStorage.getItem('userId'),
                        "id": `${id}`
                    })
                .then(() => {
                    
                    window.location.reload(true);
                });

        }
    }


    return (
        <div>
            <input
                className={Styles.comment}
                type="text"
                placeholder="Add a comment"
                onKeyDown={handleKeyDown}
            />
        </div>
    );
};

export default AddComment;