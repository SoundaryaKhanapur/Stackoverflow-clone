import React from 'react';
import Styles from './AddComment.css';
import Moment from 'react-moment';


const CommentsCard = ({ comments,
    userId,
    createdAt }) => {

    return (
        <div className={Styles.commentscontainer}>
            <div className={Styles.comments}>
                <div className={Styles.commentbox}>
                    {comments}
                </div>
                <div className={Styles.commentuserdetails}>
                    User:{userId} - {<Moment fromNow>{createdAt}</Moment>}
                </div>
            </div>
        </div>

    )
}

export default CommentsCard;