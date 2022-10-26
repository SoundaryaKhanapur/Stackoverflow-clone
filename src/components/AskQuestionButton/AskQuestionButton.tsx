import React, { useEffect, useState } from 'react';
import Styles from './AskQuestionButton.css';

import { Link } from 'react-router-dom';

const AskQuestionButton = () => {

    return (

        <div className={Styles.button}>
            <Link to="/AskQuestion" className={Styles.buttonStyling}>
                <p className={Styles.btntext}>Ask Question</p>
            </Link>
        </div>

    );
}

export default AskQuestionButton;