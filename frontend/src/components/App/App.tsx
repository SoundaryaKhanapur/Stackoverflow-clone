// SPDX-License-Identifier: MIT
// Copyright (c) 2020 Daimler TSS GmbH
import React from 'react';
import NavBar from '../NavBar/NavBar';
import { BrowserRouter, Route, withRouter } from 'react-router-dom';
import './App.css';
import Home from '../Home/Home';
import AskQuestion from '../Form/AskQuestion';
import Tags from '../Tags/Tags';
import QuestionDetails from '../QuestionDetails/QuestionDetails';
import TagQuestions from '../TagQuestions/TagQuestions';
import SearchResult from '../SearchBox/SearchResult';
import GoHome from '../GoHome/GoHome';
import LogOff from '../LogOff/LofOff';

import SignUp from '../SignUpSignIn/SignUp';
import SignIn from '../SignUpSignIn/SignIn';
import SignUpSignIn from '../SignUpSignIn/SignUpSignIn';
import Questions from '../Home/Questions';

const Main = withRouter(({ location }) => {
  return (
    <div>
      {
        location.pathname != '/' && location.pathname != '/SignUp' && location.pathname != '/SignIn' && <NavBar />
      }

      <Route path="/" exact component={SignUpSignIn} />
      <Route path="/home" exact component={Home} />
      <Route path="/questons" exact component={Questions} />
      <Route path="/AskQuestion" component={AskQuestion} />
      <Route path="/tags" component={Tags} />
      <Route path="/questionDetails" component={QuestionDetails} />
      <Route path="/tagged" component={TagQuestions} />
      <Route path="/searchResult" component={SearchResult} />
      <Route path="/logoff" exact component={LogOff} />

      <Route path="/SignUp" component={SignUp} />
      <Route path="/SignIn" component={SignIn} />

      {
        location.pathname != '/' && location.pathname != '/SignUp' && location.pathname != '/SignIn' && <GoHome />
      }
    </div>
  )
}

)


const App = () => {




  return (
    <div>
      <BrowserRouter>
        <Main />
      </BrowserRouter>
    </div>
  );
};

export default App;
