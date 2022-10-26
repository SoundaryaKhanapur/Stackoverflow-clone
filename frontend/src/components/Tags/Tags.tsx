import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Styles from './Tags.css';
import TagsCard from './TagsCard';
import Header from '../Header/Header';

const Tags = () => {
    const [tag, setTag] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8081/api/tags`).then((res) => {
            setTag(res.data);

            console.log(res.data);
        });

    }, []);
    return (
        <div>
            <Header />
            <div className={Styles.tag}>
                <div className={Styles.tagcardcontainer}>
                    {tag.map(({ tagname, createdAt, questionsCount }) => {
                        return <TagsCard tagname={tagname} createdAt={createdAt} questionsCount={questionsCount} />;
                    })}

                </div>
            </div>
        </div>
    );
};

export default Tags;