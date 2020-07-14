<script src="https://unpkg.com/axios/dist/axios.min.js"/>

var config = (function($){

    axios.get("http://baidu.com",{}
        .then(function (response) {
            console.log(response.data);
        }))
})(window.config||{});