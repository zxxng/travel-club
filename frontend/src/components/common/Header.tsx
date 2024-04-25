import React from 'react';
import { CircleX } from 'lucide-react';

const Header = ({ children }: { children: React.ReactNode }) => {
  return <header className="flex gap-5 items-end mb-5">{children}</header>;
};

const Title = ({ title }: { title: string }) => {
  return <h1 className="text-3xl font-bold">{title}</h1>;
};

const SubTitle = ({
  subTitle,
  onClick,
}: {
  subTitle: string;
  onClick: () => void;
}) => {
  return (
    <>
      <p className="text-primary-blue text-xl font-semibold">{subTitle}</p>
      <CircleX
        className="cursor-pointer text-accent-red w-5 ml-[-10px]"
        onClick={onClick}
      />
    </>
  );
};

Header.Title = Title;
Header.SubTitle = SubTitle;

export default Header;
