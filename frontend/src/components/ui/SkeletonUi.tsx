import React from 'react';
import { Container, Flex, Text, Skeleton } from '@radix-ui/themes';

const SkeletonUi = () => {
  return (
    <Container size="4" className="mt-5">
      <Flex direction="column" gap="2">
        <Text>
          <Skeleton>Lorem ipsum dolor sit amet.</Skeleton>
        </Text>

        <Skeleton>
          <Text>Lorem ipsum dolor sit amet</Text>
        </Skeleton>
        <Skeleton>
          <Text>Lorem ipsum dolor sit amet</Text>
        </Skeleton>
      </Flex>
    </Container>
  );
};

export default SkeletonUi;
