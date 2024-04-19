import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import './globals.css';
import { cn } from '@/util/utils';
import { Plane } from 'lucide-react';
import logo from '@/public/nextree_logo.svg';
import Image from 'next/image';

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
        <div className="flex flex-col max-w-[1220px] min-h-screen p-10 bg-white mx-auto text-center">
          <header className="flex gap-1 justify-center items-center mx-auto mb-6">
            <Plane
              className="w-9 h-9"
              // style={{ color: 'var(--primary-blue)' }}
            />
            <h1 className="text-3xl font-bold">Travel Club</h1>
          </header>
          <main className="grow bg-gray-300">{children}</main>
          <footer className="w-full">
            <Image src={logo} alt="logo" className="w-16 mx-auto mt-4" />
          </footer>
        </div>
      </body>
    </html>
  );
}
