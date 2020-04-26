package com.xworkzcm.main.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkzcm.main.daoapi.UserLoginDaoApi;
import com.xworkzcm.main.entity.UserEntity;
import com.xworkzcm.main.entity.UserLoginEntity;
import com.xworkzcm.main.utils.Error;

@Repository
public class UserLoginImpl implements UserLoginDaoApi {

	@Autowired
	SessionFactory factory;

	Error error = new Error();

	public Error loginDao(String emailId) {

		System.out.println("inside the dao class" + emailId);

		error.setSuccess(true);
		Session session = null;
		session = factory.openSession();

		String hql = "from UserEntity e where e.email= :emailId";

		try {
			Query query = session.createQuery(hql, UserEntity.class);
			query.setParameter("emailId", emailId);
			UserEntity entity = (UserEntity) query.uniqueResult();
			if (entity != null) {
				if (entity.getNoOfAttemp() == 3) {
					error.setMessage("Your Account has deactivated");
					error.setSuccess(false);
					return error;

				}

				error.setSuccess(true);
				return error;

			} else {

				error.setSuccess(false);
				error.setMessage("invalid credentials");
				return error;
			}

		} catch (Exception e) {
			e.printStackTrace();
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

		return error;

	}

	public Error checkPassword(UserLoginEntity entity) {
		String email = entity.getEmail();
		Error error = new Error();

		Session session = null;
		Transaction transaction = null;
		session = factory.openSession();
		String hql = "from UserEntity e where e.email= :email";
		try {

			Query query = session.createQuery(hql, UserEntity.class);
			query.setParameter("email", email);
			UserEntity userEntity = (UserEntity) query.uniqueResult();
			if (userEntity != null) {
				System.out.println("attemp password count +  " + userEntity.getNoOfAttemp());
				if (!(entity.getPassword().equals(userEntity.getPassword()))) {
					int pwdAttemp = userEntity.getNoOfAttemp();
					pwdAttemp++;
					String emailId = userEntity.getEmail();
					transaction = session.beginTransaction();
					String uddateHql = "update UserEntity s set s.noOfAttemp=:pwdAttemp where s.email=:emailId";
					query = session.createQuery(uddateHql);
					query.setParameter("emailId", emailId);
					query.setParameter("pwdAttemp", pwdAttemp);
					int count = query.executeUpdate();
					error.setSuccess(false);
					error.setMessage("invalid credentials");
					return error;

				} else {

					try {
						transaction = session.beginTransaction();
						session.save(entity);
						transaction.commit();

						error.setMessage("You Have Login Successfully");
						error.setSuccess(true);
						return error;

					} catch (Exception e) {
						transaction.rollback();
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (session != null) {
					session.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

			return error;
		}

	}

}
