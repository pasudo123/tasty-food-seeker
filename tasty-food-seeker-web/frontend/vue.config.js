
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
                pathRewrite: { '^/dev-api': '' },
                logLevel: 'debug'
            },
        }
    },

    // scss
    // module: {
    //     rules: [
    //         {
    //             test: /\/.s[ac]ss$/i,
    //             use: [
    //                 "style-loader",
    //                 "css-loader",
    //                 "sass-loader"
    //             ]
    //         }
    //     ]
    // }
}