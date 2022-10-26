import React from 'react';
import Styles from './Home.css';
import { Link } from 'react-router-dom';


const TagCard = ({ tagname }) => {

    return (
        <div className={Styles.tagscontainer}>
            {
                tagname.map((tag, index) => {
                    console.log(tag + "name")
                    return (
                        <div className={Styles.tagbox}>
                            <Link to={{
                                pathname: '/tagged',
                                state: { message: tag }
                            }}
                                className={Styles.tag}>
                                {tag}
                            </Link>
                        </div>
                    )
                })
            }

        </div>

    )
}


export default TagCard;