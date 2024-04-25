import React from 'react';
import { Plane } from 'lucide-react';
import Link from 'next/link';
import logo from '@/public/nextree_logo.svg';
import Image from 'next/image';

const Navigator = () => {
  const menuList = [
    { name: 'Travel Club', url: '/club' },
    { name: 'Community Member', url: '/member' },
    { name: 'Club Membership', url: '/membership' },
    { name: 'Social Board', url: '/board' },
    { name: 'Posting', url: '/posting' },
  ];

  return (
    <nav className="min-w-72 w-1/4 bg-primary-blue text-white relative">
      <Link
        href="/"
        className="flex gap-1 justify-center items-center mx-auto py-8 border-b-2 mb-5"
      >
        <Plane className="w-9 h-9" />
        <p className="text-3xl font-bold">Travel Club</p>
      </Link>

      <ul className="text-xl font-semibold text-center">
        {menuList.map((menu) => {
          return (
            <li key={menu.name}>
              <Link
                href={menu.url}
                className="block py-4 hover:py-5 hover:bg-white hover:text-black transition"
              >
                {menu.name}
              </Link>
            </li>
          );
        })}
      </ul>
      <div className="w-full absolute bottom-7">
        <Image src={logo} alt="logo" className="w-16 mx-auto" />
      </div>
    </nav>
  );
};

export default Navigator;
