import {
  UseQueryResult,
  useQuery,
  UseQueryOptions,
} from '@tanstack/react-query';

// GET 요청에서 활용하는 hook
const useApiQuery = <Data = unknown>(
  queryParam: string,
  options?: Omit<UseQueryOptions<Data, Error>, 'queryKey'>,
): UseQueryResult<Data, Error> => {
  const queryFn = async () => {
    try {
      const response = await fetch(
        `${process.env.NEXT_PUBLIC_API_BASE_URL}${queryParam}`,
        {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
        },
      );

      if (!response.ok) {
        const errorResponse = await response.json();
        throw new Error(errorResponse.message || 'An unknown error occurred');
      }

      return await response.json();
    } catch (error) {
      if (error instanceof Error) {
        throw new Error(`${error.message}`);
      }
      throw new Error('An unknown network error occurred');
    }
  };

  return useQuery<Data, Error>({
    queryFn: queryFn,
    queryKey: [queryParam],
    ...options,
  });
};

export default useApiQuery;
