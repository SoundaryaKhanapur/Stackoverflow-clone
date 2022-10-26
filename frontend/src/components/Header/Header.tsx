import React from 'react';
import SearchBox from '../SearchBox/SearchBox';
import Styles from './Header.css';
import { Link } from 'react-router-dom';


const Header = () => {
  return (
    <div className={Styles.header}>
      <div className={Styles.headerleft}>
        <Link to="/" className={Styles.logotext}>
          Daimler
        </Link>
        {/* <p className={Styles.logotext}>Daimler</p> */}
      </div>

      <div className={Styles.headercenter}>
        <SearchBox />
      </div>
      <div className={Styles.headerright}>
        <Link to="/tags" className={Styles.tagStyling}>
          Tags
        </Link>
      </div>
    </div>
  );
};

export default Header;
