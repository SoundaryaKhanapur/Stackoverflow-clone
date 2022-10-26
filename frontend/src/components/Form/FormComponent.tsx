import 'bootstrap/dist/css/bootstrap.css';
import React, { useState } from 'react';
import axios from 'axios';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import { Box } from '@material-ui/core';
import Button from '@material-ui/core/Button';
import { Redirect } from 'react-router';
import Styles from './FormComponent.css';
const defaultValues = {
  title: '',
  body: '',
  userId: '',
  tags: '',
};
const FormComponent = () => {
  const [formValues, setFormValues] = useState(defaultValues);
  // const [bold, setBold] = useState(false);
  const [redToHome, setRedToHome] = useState(false);

  const handleReset = (e) => {
    e.preventDefault();
    console.log('In reset');
    setFormValues(defaultValues);
    alert('Form Submitted successfully!');
    setRedToHome(true);
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Title: ', formValues.title);
    console.log('Body: ', formValues.body);
    console.log('Tags: ', formValues.tags.split(','));
    console.log(localStorage.getItem('userId'))
    const userEmailId = localStorage.getItem('userId')
    axios({
      method: 'post',
      url: 'http://localhost:8081/api/question',
      data: {
        title: formValues.title,
        body: formValues.body,
        userId: userEmailId,
        tags: formValues.tags.split(','),
      },
    });
    handleReset(e);
  };
  const handleInputChange = (e) => {
    console.log('In handle input change');
    const { name, value } = e.target;
    // if (name == 'tags') {
    //   handleKeyDown(e);
    // }
    setFormValues({
      ...formValues,
      [name]: value,
    });
  };

  // const handleKeyDown = (e) => {
  //   console.log(e.target.value);
  //   setTagSearch(e.target.value);
  // };
  // const onBoldClick = (e) => {
  //   console.log('inside Bold');
  //   setBold(!bold);
  // };
  return (
    <div className={Styles.mainDiv}>
      <Box className={Styles.boxStyle} border={1} boxShadow={6}>
        <form id="create-form" onSubmit={handleSubmit} onReset={handleReset}>
          <Grid alignItems="center" justifyContent="center" direction="column">
            <Grid item>
              <TextField
                autoComplete="off"
                id="title-input"
                name="title"
                label="Title"
                type="text"
                fullWidth
                variant="outlined"
                value={formValues.title}
                onChange={handleInputChange}
                className={Styles.title}
              />
            </Grid>
            <br />
            <Grid item>
              <TextField
                autoComplete="off"
                id="body-input"
                name="body"
                label="Body of the Question"
                type="text"
                fullWidth
                variant="outlined"
                multiline
                rows={8}
                value={formValues.body}
                onChange={handleInputChange}
                // style={{ width: '100vh', paddingTop: '15px', fontWeight: bold ? 'bold' : 'normal' }}
                className={Styles.title}
              />
            </Grid>
            <br />
            <Grid item>
              <TextField
                autoComplete="off"
                id="tags-input"
                name="tags"
                label="Tags"
                type="search"
                fullWidth
                variant="outlined"
                value={formValues.tags}
                onChange={handleInputChange}
                className={Styles.title}
              />
              {/* {tagSearch.length > 0 && (
                <Redirect to={{ pathname: '/TagSearchResult', state: { message: tagSearch } }} />
              )} */}
            </Grid>
            <Grid item style={{ alignItems: 'center', justifyContent: 'center', display: 'flex' }}>
              <Button
                variant="contained"
                color="primary"
                type="submit"
                style={{ marginTop: '15px', color: 'white', backgroundColor: '#007a93', fontFamily: 'inherit' }}
              >
                Submit
              </Button>
              {redToHome && <Redirect to={{ pathname: '/home' }} />}
              <Button
                variant="contained"
                color="primary"
                type="reset"
                style={{
                  marginTop: '15px',
                  marginLeft: '10px',
                  color: 'white',
                  backgroundColor: '#007a93',
                  fontFamily: 'inherit',
                }}
              >
                Reset
              </Button>
            </Grid>
          </Grid>
        </form>
      </Box>
    </div>
  );
};
export default FormComponent;
