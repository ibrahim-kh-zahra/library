package com.prisma.library.repositories;

import com.prisma.library.models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow,Long> {}
