'use strict';

const koa = require('koa');
const koaLogger = require('koa-logger');
const koaStatic = require('koa-static');

const app = new koa();

app.use(koaLogger());
app.use(koaStatic('src'));
app.listen(88);
console.log('listening on port 88');