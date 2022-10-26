import React from 'react';

import { Link } from 'react-router-dom';
import Button from '@material-ui/core/Button';

const SignUpSignIn = () => {
  return (
    <div>
      <div>
        <Link to="/SignUp">
          <Button
            variant="contained"
            color="primary"
            type="submit"
            style={{
              color: 'white',
              backgroundColor: '#007a93',
              fontFamily: 'inherit',
              position: 'absolute',
              top: '50%',
              left: '50%',
              transform: 'translate(-50%,-150%)',
              minWidth: '120px',
              minHeight: '50px',
            }}
          >
            Sign Up
          </Button>
        </Link>
      </div>
      <div>
        <Link to="/SignIn">
          <Button
            variant="contained"
            color="primary"
            type="submit"
            style={{
              color: 'white',
              backgroundColor: '#007a93',
              fontFamily: 'inherit',
              position: 'absolute',
              top: '50%',
              left: '50%',
              transform: 'translate(-50%,-30%)',
              minWidth: '120px',
              minHeight: '50px',
            }}
          >
            Sign In
          </Button>
        </Link>
      </div>
    </div>
  );
};

export default SignUpSignIn;
