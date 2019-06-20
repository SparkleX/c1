'use strict';

const koa = require('koa');
const koaLogger = require('koa-logger');
const koaStatic = require('koa-static');
const koaProxy = require('koa-proxy');

const app = new koa();

app.use(koaLogger());
app.use(koaStatic('src/main/resources/public'));

app.use(koaProxy({
	  host:  'http://localhost:8080',
	  match: /^\/api\//
	}));

app.listen(88);
console.log('listening on port 88');