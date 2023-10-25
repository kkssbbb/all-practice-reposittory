import React from 'react';
import PropTypes from 'prop-types'
import Head from 'next/head'
import wrapper from '../store/configureStore';
const App = ({Component})=>{
    return(
        <div>
            <Head>
            <title>노드버드</title>
            </Head>
            <Component/>
        </div>
    )
}

App.propTypes = {
    Component : PropTypes.elementType.isRequired
}


export default wrapper.withRedux(App);