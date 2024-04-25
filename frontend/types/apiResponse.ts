export type RequestData = { [key: string]: string | number };

export interface Membership {
  clubId: number;
  memberEmail: string;
  role: 'Member' | 'President';
  joinDate: string;
}

export interface Club {
  id: number;
  name: string;
  intro: string;
  foundationDay: string;
  membershipList: Membership[];
}

export interface Address {
  zipCode: string;
  zipAddress: string;
  streetAddress: string;
  country: string;
  addressType: 'Home' | 'Office';
}

export interface Member {
  email: string;
  name: string;
  nickName: string;
  phoneNumber: string;
  birthDay: string;
  addresses: Address[];
  membershipList: Membership[];
}

export interface Board {
  clubId: number;
  name: string;
  adminEmail: string;
  createDate: string;
}

export interface Posting {
  postingId: string;
  boardId: number;
  title: string;
  writerEmail: string;
  contents: string;
  writtenDate: string;
  readCount: number;
}
