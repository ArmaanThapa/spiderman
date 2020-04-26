package com.xworkzcm.main.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkzcm.main.daoapi.UserDaoApi;
import com.xworkzcm.main.entity.UserEntity;
import com.xworkzcm.main.utils.Error;

@Repository
public class UserDaoImpl implements UserDaoApi {

	Error error = new Error();

	public UserDaoImpl() {
		System.out.println("created  " + this.getClass().getSimpleName());
	}

	@Autowired
	SessionFactory factory;

	public boolean saveRegister(UserEntity userEntity) {

		Session session = null;
		Transaction transaction = null;
		session = factory.openSession();
		transaction = session.beginTransaction();

		try {

			session.save(userEntity);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		finally {
			try {

				if (session != null) {
					session.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return true;

	}

	public Error findByUserId(String user) {

		error.setSuccess(true);
		Session session = null;
		session = factory.openSession();

		try {

			System.out.println(user);
			String hql = "from UserEntity e where e.userId = :user";
			Query<UserEntity> query = session.createQuery(hql);
			query.setParameter("user", user);
			Object userEntity = query.uniqueResult();
			if (userEntity == null) {
				error.setSuccess(true);
				return error;

			} else {
				error.setSuccess(false);
				error.setMessage("User Already Exist");
				return error;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return error;

		}

		finally {
			try {
				if (session != null) {
					session.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public Error findUserByEmail(String email) {

		Session session = null;
		error.setSuccess(true);
		session = factory.openSession();
		try {

			String hql = "from UserEntity e where e.email= :email";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			UserEntity entity = (UserEntity) query.uniqueResult();

			if (entity == null) {
				error.setSuccess(true);
				return error;
			} else {
				error.setSuccess(false);
				error.setMessage("Email Id already Exist");
				return error;

			}

		} catch (Exception e) {
			e.printStackTrace();
			return error;

		} finally {
			try {
				if (session != null) {
					session.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

}