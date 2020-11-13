module.exports = {
  title: 'ScalaPy',
  tagline: 'Use Python libraries from the comfort of Scala',
  url: 'https://scalapy.dev',
  baseUrl: '/',
  onBrokenLinks: 'throw',
  favicon: 'img/favicon.ico',
  organizationName: 'facebook', // Usually your GitHub org/user name.
  projectName: 'docusaurus', // Usually your repo name.
  themeConfig: {
    navbar: {
      title: 'ScalaPy',
      logo: {
        alt: 'ScalaPy Logo',
        src: 'img/logo.svg',
      },
      items: [
        {
          to: 'docs/',
          activeBasePath: 'docs',
          label: 'Docs',
          position: 'left',
        },
        {
          href: 'https://github.com/shadaj/scalapy/blob/master/CHANGELOG.md',
          label: 'Releases',
          position: 'right',
        },
        {
          href: 'https://github.com/facebook/docusaurus',
          label: 'GitHub',
          position: 'right',
        },
      ],
    },
    footer: {
      style: 'dark',
      links: [
        {
          title: 'Docs',
          items: [
            {
              label: 'Core Concepts',
              to: 'docs/getting-started',
            },
            {
              label: 'Advanced Topics',
              to: 'docs/jupyter-notebooks',
            },
          ],
        },
        {
          title: 'Community',
          items: [
            {
              label: 'Gitter Chat',
              href: 'https://gitter.im/shadaj/scalapy',
            }
          ],
        },
        {
          title: 'More',
          items: [
            {
              label: 'GitHub',
              href: 'https://github.com/shadaj/scalapy',
            },
            {
              label: 'Releases',
              href: 'https://github.com/shadaj/scalapy/blob/master/CHANGELOG.md',
            },
          ],
        },
      ],
      copyright: `Copyright © ${new Date().getFullYear()} Shadaj Laddad. Built with Docusaurus.`,
    },
  },
  presets: [
    [
      '@docusaurus/preset-classic',
      {
        docs: {
          path: '../built-docs/target/mdoc',
          sidebarPath: require.resolve('./sidebars.js'),
          // Please change this to your repo.
          // editUrl:
          //   'https://github.com/shadaj/scalapy/edit/master/website/',
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      },
    ],
  ],
};
