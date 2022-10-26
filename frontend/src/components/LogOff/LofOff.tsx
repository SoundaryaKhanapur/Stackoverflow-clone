import React from 'react';
import { Redirect } from 'react-router';

const LogOff = () => {
  localStorage.removeItem('userId');
  localStorage.clear();
  
  return (
    <div>
      {alert('Log off successful')}
      <Redirect to={{ pathname: '/SignIn' }} />
    </div>
  );
};

export default LogOff;
