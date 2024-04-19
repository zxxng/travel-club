import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import './globals.css';
import { cn } from '@/util/utils';

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
        <main className="max-w-[1220px] bg-white mx-auto">{children}</main>
      </body>
    </html>
  );
}
