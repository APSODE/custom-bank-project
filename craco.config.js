const path = require('path');
const HtmlWebpackPlugin = require("html-webpack-plugin")
const CracoAlias = require("craco-alias")

module.exports = {
    webpack: {
        configure: (webpackConfig, { env, paths }) => {
            // webpackConfig.entry = './src/main/react/index.js';
            // paths.entry = './src/main/react/index.js';
            paths.appIndexJs = path.resolve(__dirname, "src/main/react/index.js")
            webpackConfig.entry = path.resolve(__dirname, "src/main/react/index.js")
            return webpackConfig;
        },
    },
    plugins: [
        {
            plugin: CracoAlias,
            options: {
                source: "jsconfig",
                tsConfigPath: "jsconfig.path.json"
            },
        },
    ],
    // devServer: (devServerConfig, { env, paths, proxy, allowedHost }) => {
    //     paths.appIndexJs = "./src/main/react/index.js"
    //     devServerConfig.entry = "./src/main/react/index.js"
    //     return devServerConfig;
    // },
    // plugins: [
    //     {
    //         plugin: {
    //             overrideWebpackConfig: ({ webpackConfig, cracoConfig, pluginOptions, context: { env, paths } }) => { return webpackConfig; },
    //             overrideDevServerConfig: ({ devServerConfig, cracoConfig, pluginOptions, context: { env, paths, proxy, allowedHost } }) => { return devServerConfig; },
    //         },
    //         options: {}
    //     }
    // ]
};