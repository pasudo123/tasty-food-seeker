// https://cli.vuejs.org/config/#
module.exports = {
    publicPath: '/',
    outputDir: '../src/main/resources',          // target directory 삭제방지 --no-clean
    assetsDir: 'static',
    indexPath: 'templates/index.ftl',
    filenameHashing: true,
    devServer: {
        port: 35555,
        proxy: {
            '^/dev-api/api': {
                target: 'http://localhost:8080',
                pathRewrite: {'^/dev-api': ''},
                logLevel: 'debug'
            },
        }
    },
    chainWebpack: (config) => {
        config.module
            .rule('vue')
            .use('vue-loader')
            .loader('vue-loader')
            .tap(options => {
                // https://bootstrap-vue.org/docs/reference/images
                options.transformAssetUrls = {
                    img: 'src',
                    image: 'xlink:href',
                    'b-avatar': 'src',
                    'b-img': 'src',
                    'b-img-lazy': ['src', 'blank-src'],
                    'b-card': 'img-src',
                    'b-card-img': 'src',
                    'b-card-img-lazy': ['src', 'blank-src'],
                    'b-carousel-slide': 'img-src',
                    'b-embed': 'src'
                }

                return options;
            })
    }
}