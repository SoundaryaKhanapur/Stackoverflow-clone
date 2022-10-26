import React from 'react';
import Styles from './Home.css';
import { Link } from 'react-router-dom';
import TagCard from './TagCard';
import Moment from 'react-moment';

const HomeCard = ({
    question,
    questionDetails,
    questionId,
    tagname,
    views,
    answersCount,
    createdAt,
    votes
}) => {

    return (

        <div className={Styles.homecard}>
            <div className={Styles.homecardleft}>
                <div className={Styles.votes}>
                    {votes}
                    <div>votes</div>
                </div>
                <div className={Styles.answers}>
                    {answersCount}
                    <div> answers</div>
                </div>
                <div className={Styles.views}>
                    {views} views
                </div>
            </div>
            <div className={Styles.homecardright}>
                <div className={Styles.homecarddetails} >
                    <Link to={{
                        pathname: '/questionDetails',
                        state: { message: questionId }
                    }}
                        className={Styles.questionheading}>
                        {question}
                    </Link>

                    <p className={Styles.questiondetails}>{questionDetails}</p>

                    <div>

                        {
                            [tagname].map((tag, index) => {
                                console.log(tag, index)
                                return <TagCard
                                    key={index}
                                    tagname={tag}
                                />
                            })
                        }

                    </div>

                    <div className={Styles.asked}>
                        Asked: {<Moment fromNow>{createdAt}</Moment>
                        }
                    </div>

                </div>
            </div>

        </div>
    );
}

export default HomeCard;