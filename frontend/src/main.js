import { createApp } from 'vue'
import { router } from '@/router/router'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const axios = require('axios')
const app = createApp(App)

app.config.globalProperties.$axios = axios;

app.use(ElementPlus)
app.use(router)
app.mount('#app')

