
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
            '/dev-api': {
                target: 'http://localhost:8080/api'
            }
        }
    }
}