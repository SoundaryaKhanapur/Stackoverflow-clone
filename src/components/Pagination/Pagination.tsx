import React, { useState } from 'react';
import Styles from './Pagination.css';
import ReactPaginate from "react-paginate";

const Pagination = ({ pageCount, changePage }) => {


    return (
        <ReactPaginate
            previousLabel={"Prev"}
            nextLabel={"Next"}
            pageCount={pageCount}
            onPageChange={changePage}
            containerClassName={Styles.paginationBttns}
            previousLinkClassName={Styles.previousBttn}
            nextLinkClassName={Styles.nextBttn}
            disabledClassName={Styles.paginationDisabled}
            activeClassName={Styles.paginationActive}
        />
    )
}

export default Pagination;