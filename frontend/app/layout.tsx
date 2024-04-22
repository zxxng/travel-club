import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import './globals.css';
import { cn } from '@/util/utils';
import '@radix-ui/themes/styles.css';
import { Theme } from '@radix-ui/themes';
import Navigator from '@/components/common/Navigator';

const inter = Inter({ subsets: ['latin'] });

export const metadata: Metadata = {
  title: 'Traval Club',
  description: 'Nextree 입사 코칭 발표 과제',
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={cn('w-screen min-h-screen bg-primary-blue', inter.className)}
      >
        <Theme className="flex">
          <Navigator />
          <main className="grow px-14 py-10">{children}</main>
        </Theme>
      </body>
    </html>
  );
}
