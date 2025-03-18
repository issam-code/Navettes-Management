const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  // devServer: {
  //   proxy: "http://localhost:3001",
  // },
  transpileDependencies: true,
  chainWebpack: config => {
    config.module.rules.delete('eslint');
}
});
