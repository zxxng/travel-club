import React from 'react';
import Header from '@/components/common/Header';
import SectionMenu from '@/components/common/SectionMenu';

const ClubMenuPage = () => {
  return (
    <div>
      <Header>
        <Header.Title title="Club Menu" />
      </Header>
      <SectionMenu title="신규 등록">등록버튼</SectionMenu>
      <SectionMenu title="관리">인풋</SectionMenu>
    </div>
  );
};

export default ClubMenuPage;
