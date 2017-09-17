module.exports = {
  "parser": "babel-eslint",
  "env": {
    "browser": true,
    "commonjs": true,
    "es6": true
  },
  "extends": "airbnb",
  "rules": {
    "quotes": [
      "error",
      "single",
      {
        "allowTemplateLiterals": true
      }
    ],
    "no-unused-expressions": [
      "error",
      {
        "allowShortCircuit": true,
        "allowTernary": true
      }
    ],
    "jsx-a11y/label-has-for": 1,
    "jsx-a11y/no-static-element-interactions": 1,
    "no-nested-ternary": [
      1
    ],
    "react/prop-types": 0, //Only disable for workshop!
  }
};
