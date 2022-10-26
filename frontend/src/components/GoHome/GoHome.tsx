import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';

import homeIconWhite from './../../assets/images/home_white.png';

import Styles from './GoHome.css';

class GoHome extends Component {
    navigateToHome = () => {
        const { history } = this.props;
        history.push('/home')
    }
    render() {
        const { location } = this.props;

        return (
            <button className={Styles.gohomebtn} onClick={this.navigateToHome}>
                <img
                    className={Styles.homeicon}
                    src={homeIconWhite}
                    alt="homeicon"
                />
            </button>
        );
    }
}

export default withRouter(GoHome);