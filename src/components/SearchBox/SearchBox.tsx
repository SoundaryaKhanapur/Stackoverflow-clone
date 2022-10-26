import React, { useState } from 'react';

import Styles from './SearchBox.css';
import { Redirect } from 'react-router-dom';



const SearchBox = () => {

    const [search, setSearch] = useState('')

    // search request only when enter key is hit.
    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            console.log(event.target.value)
            setSearch(event.target.value)
        }
    }


    return (
        <div>
            <input
                className={Styles.search}
                type="search"
                placeholder="Search"
                onKeyDown={handleKeyDown}
            />

            {search.length > 0 &&

                <Redirect to={{
                    pathname: '/searchResult',
                    state: { message: search }
                }}
                />
            }

        </div>

    )
}

export default SearchBox;