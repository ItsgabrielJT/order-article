import React from 'react'
import { useRouteError } from 'react-router-dom';

function ErrorPage() {
  
    return (
      <div id="wrapper">
            <img src="https://i.imgur.com/qIufhof.png" />
            <div id="info">
                <h3>This page could not be found</h3>
            </div>
        </div >
    );
}

export default ErrorPage