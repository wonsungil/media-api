const MiniCssExtractPlugin = require('mini-css-extract-plugin')

module.exports = {
  outputDir: './main/resource/static',
  devServer: {
    port: 3001,
    proxy: {
      '^/api': {
        target: 'http://localhost:9100',
        changeOrigin: true,
        logLevel: 'debug',
        pathRewrite : {"/api":""}
      }
    }
  },
  configureWebpack: {

  },
  css : {
    loaderOptions : {
      scss: {
        additionalData: `
          @import "~@/assets/styles/index.scss";
        `
      },
    }
  }
}