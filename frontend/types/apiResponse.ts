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
  membershipDtoList: Membership[];
}
