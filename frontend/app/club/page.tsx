'use client';

import React from 'react';
import Header from '@/components/common/Header';
import ClubDialog from '@/components/dialog/ClubDialog';
import SearchBar from '@/components/common/SearchBar';
import ClubTable from '@/components/table/ClubTable';
import SectionMenu from '@/components/common/SectionMenu';

const ClubMenuPage = () => {
  return (
    <div>
      <Header>
        <Header.Title title="Club Menu" />
      </Header>
      <SectionMenu title="Register">
        <ClubDialog />
      </SectionMenu>
      <SearchBar
        title="Find a Club Name"
        placeholder="Search the Club name..."
      />
      <ClubTable />
    </div>
  );
};

export default ClubMenuPage;
