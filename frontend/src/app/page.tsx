import { Plane } from 'lucide-react';
import Link from 'next/link';

export default function Home() {
  return (
    <section className="w-full">
      <h2 className="mt-36 my-auto text-2xl text-primary-blue font-bold text-center">
        <Plane className="block mx-auto w-36 h-36 mb-10" />
        <Link
          href="\club"
          className="p-8 border-4 border-primary-blue rounded-3xl hover:bg-primary-blue transition hover:text-white"
        >
          Start Travel Club!
        </Link>
      </h2>
    </section>
  );
}
