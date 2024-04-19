import type { Config } from 'tailwindcss';

const config: Config = {
  content: [
    './pages/**/*.{js,ts,jsx,tsx,mdx}',
    './components/**/*.{js,ts,jsx,tsx,mdx}',
    './app/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      colors: {
        'primary-blue': 'var(--primary-blue)',
        'base-black': 'var(--base-black)',
        'accent-red': 'var(--accent-red)',
        'medium-gray': 'var(--medium-gray)',
        'light-gray': 'var(--light-gray)',
      },
    },
  },
  plugins: [],
};
export default config;
