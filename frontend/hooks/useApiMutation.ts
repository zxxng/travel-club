import { UseMutationOptions, useMutation } from '@tanstack/react-query';

// POST, PUT, DELETE 요청에서 활용하는 hook
const useApiMutation = <Data = unknown, Variables = unknown>(
  url: string,
  method: 'POST' | 'PUT' | 'DELETE',
  options?: UseMutationOptions<Data, Error, Variables>,
) => {
  const mutationFn = async (variables: Variables): Promise<Data> => {
    try {
      const response = await fetch(
        `${process.env.NEXT_PUBLIC_API_BASE_URL}${url}`,
        {
          method: method,
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(variables),
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

  return useMutation<Data, Error, Variables>({ mutationFn, ...options });
};

export default useApiMutation;
