module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {},
    colors: {
      'whiteColor': '#FFFFFF',
      'blueBg': '#143CDA',
      'yellowColor': "#F0C160",
      'blueBorder': '#3145AD',
      'blueDarkBg': '#002B94',
      'greenColor': '#00FF66',
      'redColor': '#FF4141',
      'tabsText': '#B8C3FF',
      'lightBlueBg': '#E2E8FF',
      'orangeBg': '#FFE6DC',
      'lightGreenBg': '#F4FFEE',
      'lightRedBg': '#FFD3D9',
      'userLightBlue': '#F7F9FF',
    },
    fontFamily: {
      heading: ['Britanica', 'sans-serif'],
    },
    screens: {
      'sm': '640px',

      'md': '768px',

      'lg': '1024px',

      'xl': '1280px',

      '2xl': '1536px',
    }
  },
  plugins: [],
}