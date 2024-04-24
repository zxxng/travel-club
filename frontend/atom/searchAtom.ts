import { atom } from 'jotai';

export const keywordAtom = atom<string>('');

export const selectAtom = atom<string>('id');

export const queryKeyAtom = atom<string[]>([]);
