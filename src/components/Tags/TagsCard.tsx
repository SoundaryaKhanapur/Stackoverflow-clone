import React from 'react';
import Styles from './Tags.css';
import { Link } from 'react-router-dom';
import Moment from 'react-moment';

const TagsCard = ({
    tagname, createdAt, questionsCount
}) => {

    return (
        <div className={Styles.tagcard}>
            <div className={Styles.tagcarddetails} >
                <Link to={{
                    pathname: '/tagged',
                    state: { message: tagname }
                }}
                    className={Styles.tagheading}>
                    {tagname}
                </Link>
                {/* <Link to="/tagged" className={Styles.tagheading}>
                    {tagname}
                </Link> */}
                {/* <h6 className={Styles.tagheading}>{tagname}</h6> */}
                <p className={Styles.tagdetails}>tagDetails here</p>
                <div className={Styles.tagsub}>
                    <div className={Styles.tagsubleft}>
                        <div>{questionsCount}</div>
                        <div>questions</div>
                    </div>
                    <div className={Styles.tagsubright}>{<Moment fromNow>{createdAt}</Moment>
                    }</div>

                </div>


            </div>
        </div>
    );
}

export default TagsCard;