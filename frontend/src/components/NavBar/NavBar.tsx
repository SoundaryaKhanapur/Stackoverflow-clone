import './NavBar.css';
import React, { Component } from 'react';
import { stack as Menu } from 'react-burger-menu';
import { Link, withRouter, RouteComponentProps } from 'react-router-dom';
import Styles from './NavBar.css';


var styles = {
    /* Position & size the burger button. */
    bmBurgerButton: {
        position: 'fixed',
        width: '20px',
        height: '15px',
        right: '36px',
        top: '30px'
    },
    /* Color to burger. */
    bmBurgerBars: {
        background: ' #007a93',
    },


    /* Color to burger icon on hover. */
    bmBurgerBarsHover: {
        background: '#a90000'
    },
    /* Size of cross button */
    bmCrossButton: {
        height: '24px',
        width: '24px',
        right: '36px'
    },
    /* Color to cross button */
    bmCross: {
        background: '#fff'
    },
    /* Sidebar wrapper styles */
    bmMenuWrap: {
        position: 'fixed',
        height: '100vh',
        overflowX: 'hidden',
        boxShadow: '0px 4px 31px rgba(0, 0, 0, 0.15)',
    },

    bmMenu: {
        background: 'linear-gradient(39.97deg, #72e0f7 0.79%, #007a93 79.29%)',
        padding: '2.5em 1.5em 0 0',
        height: '100vh',
        overflow: 'hidden !important'
    },


    // bmItemList: {
    //     padding: '0.8em'
    // },

}

class NavBar extends Component<RouteComponentProps> {

    render() {


        return (
            <div>

                <Menu right styles={styles}>
                    <Link to="/home" className={Styles.menuitem}>
                        Home
                    </Link>
                    <Link to="/questons" className={Styles.menuitem}>
                        Questions
                    </Link>
                    <Link to="/tags" className={Styles.menuitem}>
                        Tags
                    </Link>
                    <Link to="/logoff" className={Styles.menuitem}>
                        LogOff
                    </Link>

                </Menu>
            </div>
        );
    }
}
export default withRouter(NavBar);