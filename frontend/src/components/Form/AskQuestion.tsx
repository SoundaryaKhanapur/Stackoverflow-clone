import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import FormComponent from './FormComponent';
import Header from '../Header/Header';
import Styles from './AskQuestion.css';

const AskQuestion = () => {
  return (
    <div>
      <Header />
      <div>
        <h4
          className={Styles.publicQuestionHeader}
          // style={{
          //   alignItems: 'center',
          //   fontFamily: 'inherit',
          //   color: '#007a93',
          //   display: 'flex',
          //   justifyContent: 'center',
          //   padding: '10px',
          // }}
        >
          Ask a public question
        </h4>
      </div>
      <div>
        <FormComponent />
      </div>
    </div>
  );
};
export default AskQuestion;
